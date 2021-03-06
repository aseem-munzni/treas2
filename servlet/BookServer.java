
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;



public class BookServer extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static String[] idArray = new String[] { "111111", "555555", "999999"};
    private static List<String> validIDs = Arrays.asList(idArray);
    public static Map<String, Book> books = new HashMap<String, Book>();
    static {
        Book book;
        book = new Book("Some Body", "Great Rockies", "12345", 10.15, "Western", 699, "Moving Publisher");
        books.put("12345", book);

        book = new Book("John Body", "Great Lakes", "12346", 20.86, "Western", 432, "Adventure House");
        books.put("12346", book);

        book = new Book("Samm Body", "Monkey's Jaw", "12347", 9.75, "Drama", 338, "Random House");
        books.put("12347", book);

        book = new Book("Lemon Drop", "Travel France", "22345", 10.25, "Travel", 192, "Old Publishing");
        books.put("22345", book);

        book = new Book("Tour Shipper", "Travel Greece", "22346", 21.19, "Travel", 321, "Gold Publishers");
        books.put("22346", book);

        book = new Book("John Hatfield", "Strange Stranger", "22347", 9.49, "Drama", 174, "Bold Publishers");
        books.put("22347", book);

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String genre = request.getParameter("genre");
        String author = request.getParameter("author");
        String item = request.getParameter("item");
        String empid = request.getParameter("empid");
        boolean isEmployee = (empid != null && validIDs.contains(empid));

        System.out.println("G=" + genre + " A=" + author + " ITM=" + item);

        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
	    response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();

        Set<String> setGenres = new HashSet<String>();
        Set<String> setAuthors = new HashSet<String>();
        for(Book b : books.values()){
            // remove duplicates
            setGenres.add(b.genre);  
            setAuthors.add(b.author);
        }

        switch(item) {

            case "books" :
                        out.println("<books>");
                        for(Book b : books.values()){
                            if("ALL".equalsIgnoreCase(author) || null == author){
                                out.println(b.toXML(isEmployee));
                            } else if(b.author.equalsIgnoreCase(author)) {
                                out.println(b.toXML(isEmployee));
                            } else if("ALL".equalsIgnoreCase(genre) || null == genre) {
                                out.println(b.toXML(isEmployee));
                            } else if(b.genre.equalsIgnoreCase(genre)) {
                                out.println(b.toXML(isEmployee));
                            }
                        }
                        out.println("</books>");
                        break;

            case "genres" :
                        out.println("<genres>");
                        for(String g : setGenres){
                            out.println("<genre value=\"" + g + "\" />");
                        }
                        out.println("</genres>");
                        break;


            case "authors" :
                        out.println("<authors>");
                        for(String a : setAuthors){
                            out.println("<author value=\"" + a + "\" />");
                        }
                        out.println("</authors>");
                        break;

            default:
                        out.println("<info> Unsupported Option </info>");
       };

	
    	out.flush();
    	out.close();
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request != null || response != null) {
            doTheDeletion(request.getParameter("isbn"));
        }
    }

    // For testing purposes,
    // Move business logic into separate function
    public boolean doTheDeletion(String isbn){
        if(isbn == null || "".equals(isbn)){
            // log invalid isbn.
            return false;
        }
        if(books.containsKey(isbn)){
            books.remove(isbn);
            return true;
        } else {
            // log isbn not found.
            return false;
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String isbn = request.getParameter("isbn");
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String priceStr = request.getParameter("price");
        String pagesStr = request.getParameter("pages");
        String publisher = request.getParameter("publisher");

        PrintWriter out = response.getWriter();
        out.println("<html><head></head><body>");

        try{
            double price = Double.parseDouble(priceStr);
            int pages = Integer.parseInt(pagesStr);
            Book book = new Book(author, title, isbn, price, genre, pages, publisher);
            books.put(isbn, book);
            out.println("<h3>Update Successful For ISBN #" + isbn + "</h3>");
        }catch(Exception e){
            out.println("<h3>Update Unsuccessful For ISBN #" + isbn + "</h3>");
        }

        out.println("<a href=\"/index.html\" >Return to Book List Page</a>" );
        out.println("</body></html>");
        out.flush();
        out.close();
    }

}

class Book {

    String genre = "";
    String author = "";
    String title = "";
    Double price = 0.0;
    String  isbn = "";
    int pages = 0;
    String publisher = "";

    public Book(String author, String title, String  isbn, Double price, String genre, int pages, String publisher){
        this.author = author;
        this.title = title;
        this.price = price;
        this.isbn = isbn;
        this.genre = genre; 
        this.pages = pages;
        this.publisher = publisher;
    }

    public String toXML(boolean isEmployee){
        StringBuffer sb = new StringBuffer("<book ");
        sb.append(" genre=\"" + genre + "\" ");
        sb.append(" author=\"" + author + "\" ");
        sb.append(" title=\"" + title + "\" ");
        sb.append(" price=\"" + price + "\" ");
        sb.append(" isbn=\"" + isbn + "\" ");

        if(isEmployee){
            sb.append(" pages=\"" + pages + "\" ");
            sb.append(" publisher=\"" + publisher + "\" ");
        }

        sb.append(" />");

        return sb.toString();
    }

}

