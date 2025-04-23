public class Testing 
{
    public static void startTesting()
    {
        Book book1 = new Book("Евгений Онегин", "Александр Пушкин", 1833);              //
        Book book2 = new Book("Анна Каренина", "Лев Толстой", 1878);                    //
        Book book3 = new Book("Мертвые души", "Николай Гоголь", 1842);                  //  добавляем книги и их описание
        Book book4 = new Book("Герой нашего времени", "Михаил Лермонтов", 1840);        //
        Book book5 = new Book("Мастер и Маргарита", "Михаил Булгаков", 1967);           //
        
        Library library = new Library();     // создаем новую библиотеку

        System.out.println("Все книги в библиотеке после добавления: ");
        library.addBook(book1);              //
        library.addBook(book2);              //
        library.addBook(book3);              //   добавляем книги в только что созданную библиотеку
        library.addBook(book4);              //
        library.addBook(book5);              //
        library.printAllBooks();             //   выведем список книг в библиотеке,  чтобы проверить что они были добавлены
        
        System.out.println("--------------------------------------------------------------");

        System.out.println("Все книги в библиотеке после удаления: ");
        library.removeBook(book3);           // удаляем книгу 3 из библиотеки
        library.removeBook(book5);           // удаляем книгу 5 из библиотеки
        library.printAllBooks();             // выведем список книг в библиотеке, чтобы проверить что они были удалены

        System.out.println("--------------------------------------------------------------");

        System.out.println("Найти книгу от автора: Лев Толстой ");
        for(Book book : library.findBooksByAuthor("Лев Толстой"))    // ищем все книги с автором "Лев Толстой" 
            System.out.println(book);

        System.out.println("---------------------------------------------------------------");

        System.out.println("Найти книгу, выпущенную  в 1840 году: ");
        for(Book book : library.findBooksByYear(1840))                // ищем все книги с годом выхода "1840"
            System.out.println(book);

        System.out.println("----------------------------------------------------------------");

        System.out.println("Авторы книг из данной библиотеки: ");
        library.printUniqueAuthors();           // выводим список всех авторов книг, содержащихся в библиотеке

        System.out.println("----------------------------------------------------------------");

        System.out.println("Статистика написанных книг от каждого из авторов: ");
        library.printAuthorStatistics();        // выводим статистику (количество написанных книг) для каждого автора
    }
}