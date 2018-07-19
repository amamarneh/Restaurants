package com.am.restauarnts.repo;

public class RepoFactory {
    public static ActionRepo getRepoInstance(){
        return new AppActionRepo();
    }
    public static UserRepo getUserRepo(){
        return new AppUserRepo();
    }
}
