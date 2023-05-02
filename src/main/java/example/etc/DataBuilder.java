package example.etc;

public class DataBuilder {
    public static void main(String[] args) {
        Data data = new Data.Companion.DataBuilder()
                .setName1("name1")
                .build();

        Data data2 = new Data.Companion.DataBuilder()
                .setName3("name3")
                .build();

        Data data3 = new Data.Companion.DataBuilder()
                .build();

        System.out.println(data);
        System.out.println(data2);
        System.out.println(data3);
    }
}
