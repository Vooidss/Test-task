package com;

import com.task2.Profile;
import com.task2.ProfileService;
import com.task1.Info;
import com.task2.Printer;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("---Задача 1---");

        Info info = new Info();
        Long time = System.currentTimeMillis();
        info.getProfileInfo(1L);
        Long time2 = System.currentTimeMillis();
        System.out.println("Выполнилось за : " + (time2 - time) + " ms");
        System.out.println(info.getProfileInfo(1L));

        System.out.println("---Задача 2---");

        System.out.println(Printer.prettyPrintJson(ProfileService.groupByOrgIdAndGroupId(List.of(
                new Profile(1L,0L,1L),
                new Profile(2L,0L,1L),
                new Profile(3L,0L,2L),
                new Profile(4L,1L,1L),
                new Profile(5L,1L,2L)
        ))));

    }
}

