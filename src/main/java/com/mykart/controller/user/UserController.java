package com.mykart.controller.user;

import com.mykart.exception.ResourceNotFound;
import com.mykart.model.User;
import com.mykart.service.user.UserService;
import com.mykart.validators.Identification;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Log4j2
@Validated
@Api(value = "User Data Service",
        description = "Operations pertaining to User in User Data Service")
public class UserController {

    

    @Autowired
    private UserService service;

    /**
     * get the list of all available users from database
     *
     * @return List of Users
     */
    @GetMapping()
    @ApiOperation(value = "Get list of Users", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    public List<User> getAllUsers() {
        log.debug("Executed UserController.getAllUsers() to retrive all Users data");

       // List<User> users= service.getAllUsers();
        //CollectionModel<User> resources=new CollectionModel<>(users);
        //resources.add(this.entityLinks.linkToCollectionResource(User.class));
       return service.getAllUsers();
    }

    /**
     * to save User data into database
     *
     * @param user User Object that is going to persist into database
     * @return user return User Object
     *
     *
     */
    @PostMapping()
    @ApiOperation(value = "Save User object into the database", response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    public User saveUser(
            @ApiParam(value = "User object", required = true) @RequestBody User user)  {
        log.debug("Executed UserController.saveUsers(user) to save User object");
        User savedUser=service.saveUser(user);
        // Creating links as per the hateoas principle.
       /* Resource<User> result = new Resource<>(savedUser);
        ControllerLinkBuilder linkTo = ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder
                        .methodOn(this.getClass()).getUserById(user.getUser_id()));
        result.add(linkTo.withRel("Books by author "+user.getUser_id()));  */
        return savedUser;

    }


    /**
     * @param id identifier of the User
     * @return user return User object with given identifier
     * @throws ResourceNotFound If system not find any object associated with given identifier then it
     *         will throw ResourceNotFound exception
     */
    @ApiOperation(value = "Get specific User by id", response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(
            @ApiParam(value = "User id", required = true) @PathVariable("id") @Identification int id)
            throws ResourceNotFound {

        User user = service.getUserById(id);
        System.out.println(user);
        log.debug(
                "Executed UserController.getUserById(id) to get User object with id " + id);
        if (user == null)
            throw new ResourceNotFound("Resource Not Found");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Delete User with given id, it will first check whether User with given identifier is
     * exits into database or not
     *
     * @param id identifier of the User you want to delete
     *
     * @throws ResourceNotFound If system doesn't find any User with given identifier then it will
     *         throw ResourceNotFound Exception
     */
    @ApiOperation(value = "Delete User by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(
            @ApiParam(value = "User id", required = true) @PathVariable("id") @Identification int id)
            throws ResourceNotFound {
        User user = service.getUserById(id);
        log.debug(
                "Executed UserController.deleteUser(id) to delete User object with id " + id);
        if (user == null)
            throw new ResourceNotFound("Resource Not Found");
        else {
            service.deleteUser(user);
            return new ResponseEntity<Object>(HttpStatus.OK);
        }

    }

    /**
     * update the existing User into the database, it will first check whether User with given
     * identifier is exits into database or not
     *
     * @param id identifier of the User you want to update
     * @param User User object with the data you want to update
     * @return updated return updated User object
     * @throws ResourceNotFound If User with the specified id is not exits then it will throw
     *         ResorceNotFound Exception
     */
    @ApiOperation(value = "Update User by id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!")})
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(
            @ApiParam(value = "User id", required = true) @PathVariable("id") @Identification int id,
            @ApiParam(value = "User object", required = true) @RequestBody User User)
            throws ResourceNotFound {
        User user = service.getUserById(id);
        log.debug("Executed UserController.updateUser(id,user) to update User object with id"
                + id);
        if (user == null)
            throw new ResourceNotFound("Resource Not Found");
        else {
            user.setFirst_name(User.getFirst_name());
            user.setLast_name(User.getLast_name());
            service.updateUser(user);
            return new ResponseEntity<Object>(HttpStatus.OK);
        }

    }

   

}
