package com.yang.java8;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapTest {

    public static void main(String[] args) {
        Map<Integer, ListContainer> map = Maps.newHashMap();
        List<AClass> aClassList1 = Lists.newArrayList();
        AClass aClass = new AClass(1, "zhuoli1", "haha1");
        aClassList1.add(aClass);
        aClassList1.add(new AClass(2, "zhuoli2", "haha2"));
        aClassList1.add(new AClass(3, "zhuoli3", "haha3"));

        List<AClass> aClassList2 = Lists.newArrayList();
        aClassList2.add(aClass);
        aClassList2.add(new AClass(5, "zhuoli5", "haha5"));
        aClassList2.add(new AClass(6, "zhuoli6", "haha6"));

        /*交集*/
        /*[AClass(id=1, name=zhuoli1, description=haha1)]*/
        List<AClass> intersectResult = aClassList1.stream().filter(aClassList2::contains).collect(Collectors.toList());
        System.out.println(intersectResult);

        /*并集*/
        List<AClass> unionResult = Stream.of(aClassList1, aClassList2).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        System.out.println(unionResult);

        /*差集*/
        /*[AClass(id=2, name=zhuoli2, description=haha2), AClass(id=3, name=zhuoli3, description=haha3)]*/
        List<AClass> differenceResult = aClassList1.stream().filter(x -> !aClassList2.contains(x)).collect(Collectors.toList());
        System.out.println(differenceResult);

        map.put(1, new ListContainer(aClassList1));
        map.put(2, new ListContainer(aClassList2));

        /*合并多个list*/
        List<AClass> aClassListResult = map.values().stream().flatMap(listContainer -> listContainer.getLst().stream()).collect(Collectors.toList());
//        aClassListResult = map.values().stream().map(ListContainer::getLst).flatMap(Collection::stream).collect(Collectors.toList());
        /*注意跟并集的区别*/
        System.out.println(aClassListResult);
    }

    public static class ListContainer {

        private List<AClass> lst;

        public ListContainer(List<AClass> lst) {
            this.lst = lst;
        }

        public List<AClass> getLst() {
            return lst;
        }

        public void setLst(List<AClass> lst) {
            this.lst = lst;
        }
    }

    public static class AClass {
        private Integer age;
        private String name;
        private String nick;

        public AClass(Integer age, String name, String nick) {
            this.age = age;
            this.name = name;
            this.nick = nick;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }
    }
}
