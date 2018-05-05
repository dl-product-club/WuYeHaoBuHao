package com.datanese.wuye.rest;

import com.datanese.wuye.dto.WeixinAccountDTO;
import com.datanese.wuye.po.UserPO;
import com.datanese.wuye.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

    	@GetMapping("/hello")
	public String getUsers() {
        return "hello";
	}

//	@GetMapping("/getUsers")
//	public List<UserPO> getUsers(@PageableDefault(page = 0, size = 10) Pageable pageable) {
//        PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
//		List<UserPO> users=userMapper.getAll();
//		return users;
//	}

    @PostMapping("/subscribe")
    public void subscribe(@RequestBody WeixinAccountDTO user) {
        userService.subscribe(user);
    }

    @GetMapping("/getUser/{id}")
    public UserPO getUser(@PathVariable("id") Long id) {
    	UserPO user=userService.getUser(id);
        return user;
    }
    
//    @PostMapping("/add")
//    public void save(@RequestBody UserPO user) {
//        userService.save(user);
//    }
    
    @PutMapping(value="/update/{id}")
    public void update(@RequestBody UserPO user, @PathVariable("id") Long id) {
        userService.update(user,id);
    }
    
    @DeleteMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }
    
    
}