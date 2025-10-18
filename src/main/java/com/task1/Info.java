package com.task1;

import java.util.concurrent.*;

public class Info {

    public static class ProfileInfo {

        public UserInfo userInfo;
        public CompanyInfo companyInfo;

        @Override
        public String toString() {
            return "ProfileInfo{" +
                    "userInfo=" + userInfo +
                    ", companyInfo=" + companyInfo +
                    '}';
        }
    }

    public static class UserInfo {

        public UserInfo(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public String name;
        public String age;

        @Override
        public String toString() {
            return "UserInfo{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }

    public static class CompanyInfo {

        public CompanyInfo(String id, String companyName) {
            this.id = id;
            this.companyName = companyName;
        }

        public String id;
        public String companyName;

        @Override
        public String toString() {
            return "CompanyInfo{" +
                    "id='" + id + '\'' +
                    ", companyName='" + companyName + '\'' +
                    '}';
        }
    }


    public ProfileInfo getProfileInfo(Long id ){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            CompletableFuture<UserInfo> userFuture  = CompletableFuture.supplyAsync(() -> getUserInfo(id), executorService);
            CompletableFuture<CompanyInfo>  companyFuture = CompletableFuture.supplyAsync(() -> getCompanyInfo(id), executorService);

            CompletableFuture<ProfileInfo> profile = userFuture.thenCombine(
                    companyFuture,
                        (user, company) -> {
                            ProfileInfo p  = new ProfileInfo();
                            p.userInfo = user;
                            p.companyInfo = company;
                            return p;
                        });

            return profile.get(1, TimeUnit.SECONDS);
        }catch (TimeoutException te) {
            throw new RuntimeException("Не уложились в 1 секунду при получении профиля id=" + id, te);
        } catch (ExecutionException | InterruptedException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new RuntimeException("Ошибка при получении профиля id=" + id, e);
        } finally {
            executorService.shutdownNow();
        }
    }

    private UserInfo getUserInfo(Long id) {
        sleep(900);
        return new UserInfo("Dima","20");
    }

    private CompanyInfo getCompanyInfo(Long id) {
        sleep(900);
        return new CompanyInfo("1", "ЛАЙТХАУС");
    }

    private void sleep(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
