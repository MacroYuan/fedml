package com.myproject.fedml;

import com.myproject.fedml.mbg.mapper.TaskMapper;
import com.myproject.fedml.mbg.mapper.UserMapper;
import com.myproject.fedml.mbg.model.Task;
import com.myproject.fedml.mbg.model.User;
import com.myproject.fedml.mbg.model.UserExample;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class FedmlApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TaskMapper taskMapper;

    @Test
    public void testSelect() {
        List<User> userList = userMapper.selectByExample(null);
        userList.forEach(System.out::println);

//        List<Task> taskList = taskMapper
    }
}
