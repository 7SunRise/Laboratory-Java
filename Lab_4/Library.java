import java.util.*;

public class Library 
{
    private final List<Book> books;                          // упорядоченный список всех книг в библиотеке
    private final Set<String> authors;                       // неупорядоченное множество всех авторов, книги которых есть в библиотеке
    private final Map<String, Integer> authorsBooksCount;    // список, в котором ключом является автор, а значением ключа - количество написанных книг данным автором

    public Library()                                                     // конструктор для библиотеки
    {
        books = new ArrayList<Book>();
        authors = new HashSet<String>();
        authorsBooksCount = new HashMap<String, Integer>();
    }

    public void addBook(Book book)                                       // (МЕТОД) добавление книги в библиотеку
    {
        books.add(book);                                                         // добавляем книгу в список
        String author = book.getAuthor();                                        // получаем автора данной книги
        authors.add(author);                                                     // добавляем автора в множество (если он уже там есть, ничего не произойдет)
        if(!authorsBooksCount.containsKey(author))                               // если данный автор впервые добавляет книгу в библиотеку
            authorsBooksCount.put(author, 0);                              // добавляем элемент с ключом - автором, а значением - 0 (т.к автор новый для данной библиотеки)
        authorsBooksCount.put(author, authorsBooksCount.get(author) + 1);        // прибавляем количество книг у данного автора
    }

    public void removeBook(Book book)                                     // (МЕТОД) удаляем книгу из библиотеки
    {
        books.remove(book);                                                        // удаляем книгу из списка
        String author = book.getAuthor();                                          // получаем автора данной книги
        authorsBooksCount.put(author, authorsBooksCount.get(author) - 1);          // уменьшаем количество книг, содержащихся в библиотеке, для данного автора
    }

    public List<Book> findBooksByAuthor(String author)                   // (МЕТОД) находим все книги, написанные данным автором
    {
        List<Book> result = new ArrayList<Book>();                                  // создаем пустой список, который будет являться нашим ответом
        for(Book book : books)                                                      // проходимся по всем книгам из нашей библиотеки
        {
            if(book.getAuthor().equals(author))                                     //  если автор книги совпадает с нужным автором
                result.add(book);                                                   //  добавляем книгу в список
        }
        return result;                                                              // возвращаем результат
    }

    public List<Book> findBooksByYear(int year)                         // (МЕТОД) находим все книги с определенным годом выхода
    {
        List<Book> result = new ArrayList<Book>();                                  //  создаем пустой список, который будет являться нашим ответом
        for(Book book : books)                                                      // проходимся по всем книгам из нашей библиотеки
        {
            if(book.getYear() == year)                                              //  если год выпуска совпадает с нужным годом
                result.add(book);                                                   //  добавляем книгу в список
        }
        return result;                                                              //  возвращаем результат
    }

    public void printAllBooks()                                        // (МЕТОД) выводим все книги из библиотеки
    {
        System.out.println();
        for(Book book : books)                                                     // проходимся по всем книгам из нашей библиотеки
            System.out.println(book);                                              // выводим книгу, используя переопределенный метод
    }

    public void printUniqueAuthors()                                   // (МЕТОД) выводим всех авторов книг из нашей библиотеки
    {
        System.out.println();
        for(String author : authors)                                               // проходимся по всем авторам книг данной библиотеки
            System.out.println(author);                                            // выводим автора
    }

    public void printAuthorStatistics()                                                                     // (МЕТОД) выводим статистику написанных книг, которые находятся в библиотеке
    {
        System.out.println();
        for(String author : authorsBooksCount.keySet())                                                                 //  проходимся по всем авторам (т.е. по ключам списка)
            System.out.println("Автор: " + author + "; Количество книг от данного автора: " + authorsBooksCount.get(author));          //  выводим информацию (т.е. автора (ключ) и кол. книг (значение))
    }

}