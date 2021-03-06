package br.com.umake.interceptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Lazy;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.umake.controller.AdmController;
import br.com.umake.controller.AdmUserController;
import br.com.umake.dao.AdmUserDao;
import br.com.umake.model.AdmPermission;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;

@Intercepts
@Lazy
public class AutenticationInterceptor implements Interceptor {

	private final AdmUserControl user;
	private final Result result;
	private final AdmUserDao userDao;
	private final Session session;

	public AutenticationInterceptor(AdmUserControl user, Result result, AdmUserDao uDao, Session session) {

		this.result = result;
		this.user = user;
		this.userDao = uDao;
		this.session = session;

	}

	public boolean accepts(ResourceMethod method) {

		return method.getMethod().isAnnotationPresent(Restrictable.class);

	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object obj) throws InterceptionException {

		Transaction tx = this.session.beginTransaction();

		try {

			if (this.user.isLogged()) {
				
				//Update user's session
				this.user.updateUser(this.userDao.get(this.user.getAdmUser()));

				if (this.onlyRestrictable(method)) {
					
					stack.next(method, obj);

				} else {

					if (this.user.getAdmUser().hasAllNecessariesPermissions(
							this.recoveryNecessariesPermissions(method))) {

						stack.next(method, obj);

					} else {
						
						this.result.include("errorPermissions", this.recoveryNecessariesPermissions(method));
						this.result.redirectTo(AdmController.class).index();

					}

				}

				this.user.getAdmUser().setDateLastVisit(new Date());
				this.userDao.updateLastDate(this.user.getAdmUser());

			} else {
				
				this.result.redirectTo(AdmUserController.class).formLogin();

			}

			tx.commit();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			tx = null;
			this.session.close();

		}

	}

	private List<AdmPermission> recoveryNecessariesPermissions(ResourceMethod method) {

		List<AdmPermission> permissions = new ArrayList<AdmPermission>(4);

		Restrictable restrictable = method.getMethod().getAnnotation(
				Restrictable.class);
		PermissionAnnotation[] permissionsOfRestrictable = restrictable
				.permissions();

		for (PermissionAnnotation permissionAnnotation : permissionsOfRestrictable) {

			if (!permissionAnnotation.context().equals("")
					&& permissionAnnotation.permissionsTypes().length > 0) {

				String context = permissionAnnotation.context();

				for (PermissionType permissionType : permissionAnnotation
						.permissionsTypes()) {

					AdmPermission permissionAdm = new AdmPermission();
					permissionAdm.setContext(context);
					permissionAdm.setType(permissionType.name());

					permissions.add(permissionAdm);

				}
			}

		}

		return permissions;

	}

	private Boolean onlyRestrictable(ResourceMethod method) {

		Restrictable restrictable = method.getMethod().getAnnotation(Restrictable.class);

		br.com.umake.permissions.PermissionAnnotation[] permissions = restrictable.permissions();

		if (permissions.length == 1) {

			if (permissions[0].context().equals("") || permissions[0].permissionsTypes().length == 0) {

				return true;

			}

		} else if (permissions.length == 0) {

			return true;

		}

		return false;
	
	}

}