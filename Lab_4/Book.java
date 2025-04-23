public class Book
{
    private final String title;                 // название книги
    private final String author;                // автор книги
    private final int year;                     // год выпуска книги

    public Book(String title, String author, int year)         // конструктор для книги
    {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString()                         // (ПЕРЕОПРЕДЕЛЕНИЕ) (МЕТОД) переопределим toString для корректной работы println
    {
        return "Название: " + title + "; Автор: " + author + "; Год выпуска: " + year; 
    }

    @Override
    public int hashCode()                            // (ПЕРЕОПРЕДЕЛЕНИЕ) (МЕТОД)  перепоределим hashCode для корректной работы HashSet и HashMap
    {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object object)             // (ПЕРЕОПРЕДЕЛЕНИЕ) (МЕТОД) переопределим equals для корректной работы equals
    { 
        if(!(object instanceof Book)) 
            return false;
        Book bookObject = (Book) object;
        return (this.author.equals(bookObject.author)) && (this.author.equals(bookObject.author)) && (this.year == bookObject.year);   // сравниваем названия, авторов и годы выпуска
    }

    public String getTitle()                       // (МЕТОД) получить название книги
    {
        return title;
    }

    public String getAuthor()                     // (МЕТОД) получить автора книги
    {
        return author;
    }

    public int getYear()                          // (МЕТОД) получить год выпуска книги
    {
        return year;
    }
}