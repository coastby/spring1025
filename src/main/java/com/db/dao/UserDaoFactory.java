package com.db.dao;

public class UserDaoFactory {
    //커넥션을 이용해 UserDao를 조립해주는 메서드 생성
    public UserDao awsUserDao() {
        return new UserDao(new AwsConnectionMaker());
    }
}
