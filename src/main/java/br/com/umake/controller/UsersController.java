package br.com.umake.controller;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.ForwardToDefaultViewInterceptor;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.umake.dao.UserDao;
import br.com.umake.interceptor.UserControl;
import br.com.umake.model.User;
import br.com.umake.permissions.PermissionAnnotation;
import br.com.umake.permissions.PermissionType;
import br.com.umake.permissions.Restrictable;


@Resource
public class UsersController { 

	private UserControl userControl;
	private UserDao userDao;
	private Result result;
	private Validator validator;
	
	public UsersController(UserControl userControl, UserDao userDao, Result result, Validator validator) {
	
		this.userControl = userControl;
		this.userDao = userDao;
		this.result = result;
		this.validator = validator;
		
	}
	

	@Get
	@Path("adm/users/login")
	public void formLogin() {
		
		if( this.userControl.isLogged() ) 
			this.result.redirectTo(AdministrationController.class).index();
			
	}

	@Post
	@Path("adm/users/login")
	public void login(final User user) { // falta validar usuário pelo servidor.
		
		User recovery = this.userDao.findUser(user);
		
		if (recovery == null) {

			validator.add(new ValidationMessage("Login e/ou senha inválidos",""));

		}
		
		this.userControl.login(recovery);

		validator.onErrorUsePageOf(this).formLogin();
		
		result.redirectTo(AdministrationController.class).index();

	}

	@Path("adm/users/logout")
	@Restrictable
	public void logout() {
		
		this.userControl.logout();
		this.result.redirectTo(this).formLogin();

	}
	
<<<<<<< HEAD
    @Get("adm/users/create")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void formCreate() {
    	
	}
	
	@Get("adm/users")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void getAllUsers(){
		
		System.out.println("Listando todos os usuários");
		
	}
	
	@Post("adm/users")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.CREATE}) }) 
	public Boolean create(final User user) {
=======
    @Create
	@Get
	@Path("/create")
	public Boolean create() {
    	
<<<<<<< HEAD
    	System.out.println("ate aki porra");

/*    	this.userDao.insertUser(user);
    	
    	System.out.println("cadastrado com sucesso!");*/
>>>>>>> f75ada794862b68edc54ee4ef66e0d4dd68858cc
    	
=======
    	if(this.userDao.insertUser(user)){
    		// ERRADOOOOOOOOOOOOOOOO AINDA mostra que foi inserido e joga para listagem de usuário
    		this.result.redirectTo(UsersController.class).formCreate();
    	}else{
    		this.result.redirectTo(UsersController.class).formCreate();
    	}
    	    	
>>>>>>> upstream/master
		return true;
	}
	
<<<<<<< HEAD
	@Get("adm/users/{user.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.VIEW})}) 
	public void getUser( User user ){
		
		this.result.include("user", this.userDao.getUser(user));
		
		this.result.forwardTo(this).formCreate();
				
	}
	
	@Put("adm/users/{user.id}")
	@Restrictable(permissions={ @PermissionAnnotation(context="USER", permissionsTypes = { PermissionType.EDIT})}) 
	public User editUser(User user){
		
		return null;
		
=======
/*    @Get
	@Create
	@Path("/create") 
<<<<<<< HEAD
	public void formCreateUser() {

	}*/
=======
	public void create() {
    	
>>>>>>> f75ada794862b68edc54ee4ef66e0d4dd68858cc
	}
>>>>>>> upstream/master
	
    @Delete("adm/users/{user.id}")
    @Restrictable(permissions={@PermissionAnnotation(context="USER", permissionsTypes={ PermissionType.DELETE} )})
	public Boolean delete(final User user){
		System.out.println("deletou");
		return true;
	}

}
