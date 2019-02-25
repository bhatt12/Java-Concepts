import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Java8Concepts{
    public static void main(String[] args)
    {
        /*1. Functional Interfaces
        2. For Each
        3. Default and static methods
        4. Streams*/
        //functInterfAndLambdaExp();
        //forEachExample();
        streamsDemo();
    }
    public static void forEachExample(){
        List<Integer> integersList = new ArrayList<>();
        for(int number = 1; number <= 10; number++){
            integersList.add(number);
        }
        //using iterator
        Iterator<Integer> integerIterator = integersList.iterator();
        while(integerIterator.hasNext()){
            System.out.println(integerIterator.next());
        }
        //using foreach
        integersList.forEach(a -> System.out.println(a));

        //by own consumer
        //MyConsumer myConsumer = new MyConsumer();
        //integersList.forEach(myConsumer);

        System.out.println("Using Acceot and Andthen");
        Consumer<List<Integer>> listConsumer = list ->
        {
            for(int index=0; index < list.size();index++)  {
                list.set(index, 2*list.get(index));
            }
        };
        Consumer<List<Integer>> displayConsumer = list->list.stream().forEach(listItem->System.out.println(listItem));
        listConsumer.andThen(displayConsumer).accept(integersList);
    }
    public static void functInterfAndLambdaExp(){

        GetConvert getConvert1 = (a) -> System.out.println((int)a);
        getConvert1.convert('A');

        GetConvert getConvert2 = (a) -> System.out.println((double)a);
        getConvert2.convert('A');
    }
    public static void streamsDemo(){
        List<Integer> numbers = new ArrayList<Integer>();
        for(int num=0;num < 10;num++){
            numbers.add(num);
        }
        Stream<Integer> evenNumbers = numbers.stream().filter(p ->(p%2 == 0));
        evenNumbers.forEach(a->System.out.println(a));
    }
}
interface BreedA{
    default void makeBreed(){
        System.out.println("BreedA");
    }
}
interface BreedB{
    default void makeBreed(){
        System.out.println("BreedB");
    }
}
//diamond problem is there
/*class Hybrid implements BreedA, BreedB{
   //cannot redefine
    *//*void makeBreed(){

    }*//*
}*/
@FunctionalInterface
interface GetConvert{
    void convert(char ch);
}
class MyConsumer implements Consumer<Integer>{
    @Override
    public void accept(Integer integer) {
        System.out.println(integer);
    }
}