//package com.yang.spring;
//
//import com.yang.spring.bean.User;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//
//public class MainTest {
//
//    public static void main(String[] args) throws Exception {
////        test1();
//        test2();
//    }
//
//    private static void test2() throws Exception {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).GET().build();
//        HttpResponse<String> response;
//        try {
//            response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
//            System.out.println(completableFuture.get().body());
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void test1() {
//        User user = new User() {
//            @Override
//            public String getName() {
//                return "我是重写的名字 王大";
//            }
//        };
//        System.out.println(user.getName());
//        Map<String, String> map = Map.of("a", "c", "b", "ddd");
//        Map<String, String> ofEntries = Map.ofEntries(Map.entry("a", "a"), Map.entry("b", "b"));
//        System.out.println(map);
//        System.out.println(ofEntries);
//
//        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        // takeWhile 不满足就推出；filter 则是过滤全部
//        list.stream().takeWhile(i -> i < 5).forEach(System.out::println);
//        System.out.println("--------------");
//        // 与takeWhile刚好相反
//        list.stream().dropWhile(i -> i < 5).forEach(System.out::println);
//    }
//}
