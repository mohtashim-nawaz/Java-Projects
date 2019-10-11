/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.*;
import java.util.Scanner;

/**
 *
 * @author harsh
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library","harsh","iiita123");

            Scanner in = new Scanner(System.in);
            Integer an,ans;
            String str,s,bid,bname,bauth,sroll,sname,date;

            while(true)
            {
                System.out.println("Select any number corresponding to the options:");
                System.out.println("1. Enter as Admin");
                System.out.println("2. Enter as Student");
                System.out.println("3. Exit");
                an = in.nextInt();

                if (an==1) {
                    System.out.println("Select any number corresponding to the options:");
                    System.out.println("1. Add book");
                    System.out.println("2. Delete Book");
                    System.out.println("3. Edit Book info");
                    System.out.println("4. Issue Book");
                    System.out.println("5. Return Book");
                    System.out.println("6. View Books of a Student");
                    System.out.println("7. View History of Books issued to a student");
                    System.out.println("8. Back to Main Menu");
                     ans = in.nextInt();

                    if (ans==1) {
                        System.out.println(" Enter book ID");
                        bid= in.next();
                        System.out.println(" Enter book name");
                        bname= in.next();
                        System.out.println(" Enter book author");
                        bauth= in.next();

                        PreparedStatement stmt=con.prepareStatement("insert into Books values(?,?,?,?,?)");
                        stmt.setString(1,bid);//1 specifies the first parameter in the query
                        stmt.setString(2,bname);
                        stmt.setString(3,bauth);
                        stmt.setInt(4,0);
                        stmt.setString(5,"NULL");

                        int i=stmt.executeUpdate();
                        System.out.println(i+" records inserted\n\n");


                    }
                    if (ans==2) {
                        System.out.println(" Enter book ID");
                        bid= in.next();

                        PreparedStatement stmt=con.prepareStatement("DELETE FROM Books WHERE book_id=?");
                        stmt.setString(1,bid);//1 specifies the first parameter in the query

                        int i=stmt.executeUpdate();
                        System.out.println(i+" records deleted\n\n");


                    }
                    if (ans==3) {
                        System.out.println(" Enter book name you want to edit info");
                        bname= in.next();
                        System.out.println(" Enter the new value for book id");
                        bid =in.next();
                        System.out.println(" Enter the new value for book author");
                        bauth =in.next();

                        PreparedStatement stmt=con.prepareStatement("UPDATE Books SET book_id = ?, author = ? WHERE book_name = ?;");
                        stmt.setString(1,bid);//1 specifies the first parameter in the query
                        stmt.setString(2,bauth);
                        stmt.setString(3,bname);

                        int i=stmt.executeUpdate();
                        System.out.println(i+" records updated \n\n");

                    }
                    if (ans==4) {
                        //ask for book id, sroll
                        System.out.println(" Enter book ID");
                        bid= in.next();
                        System.out.println(" Enter Student Roll No");
                        sroll= in.next();
                        //check if book is issued
                        PreparedStatement stmt=con.prepareStatement("select status from Books where book_id = ? ;");
                        stmt.setString(1,bid);//1 specifies the first parameter in the query
                        ResultSet rs=stmt.executeQuery();
                        rs.next();
                        int stat=rs.getInt(1);
                        if(stat==1){
                            System.out.println("The book is already issued, cant issue\n");
                            continue;
                        }

                        //check if baccha can issue
                        stmt=con.prepareStatement("SELECT COUNT(*) FROM Transactions WHERE book_id = ? and cur_status=1;");
                        stmt.setString(1,bid);//1 specifies the first parameter in the query
                        rs=stmt.executeQuery();
                        rs.next();
                        stat=rs.getInt(1);
                        if(stat>3)
                        {
                            System.out.println("Student already has "+stat+" books issued, cant issue anymore!!");
                            continue;
                        }
                        //see if book already issued
                        stmt=con.prepareStatement("SELECT COUNT(*) FROM Transactions WHERE book_id = ? and sroll=? and cur_status=1;");
                        stmt.setString(1,bid);//1 specifies the first parameter in the query
                        stmt.setString(2,sroll);//1 specifies the first parameter in the query
                        rs=stmt.executeQuery();
                        rs.next();
                        stat=rs.getInt(1);
                        if(stat==1)
                        {
                            System.out.println("Student already has issued this book, cant issue again!!\n\n");
                            continue;
                        }

                        //ask for sname, due date in format yyyy-mm-dd
                        System.out.println(" Enter student name");
                        sname= in.next();
                        System.out.println(" Enter Due Date in yyyy-mm-dd format");
                         date= in.next();
                        //update status in books
                        stmt=con.prepareStatement("UPDATE Books SET status = 1 WHERE book_id = ?;");
                        stmt.setString(1,bid);//1 specifies the first parameter in the query
                        stmt.execute();

                        //add transcation
                        stmt=con.prepareStatement("insert into Transactions(book_id,sroll,sName,cur_status,due_date) values(?,?,?,1,?);");
                        stmt.setString(1,bid);//1 specifies the first parameter in the query
                        stmt.setString(2,sroll);
                        stmt.setString(3,sname);
                        stmt.setString(4,date);

                        int i=stmt.executeUpdate();
                        System.out.println(i+" records inserted\n\n");


                    }
                    if (ans==5) {

                        System.out.println(" Enter book ID");
                        bid= in.next();
                        System.out.println(" Enter Student Roll No");
                        sroll= in.next();
                        PreparedStatement stmt=con.prepareStatement("SELECT COUNT(*) FROM Transactions WHERE book_id = ? and sroll= ? and cur_status=1;");
                        stmt.setString(1,bid);//1 specifies the first parameter in the query
                        stmt.setString(2,sroll);//1 specifies the first parameter in the query
                        ResultSet rs=stmt.executeQuery();
                        rs.next();
                        int stat=rs.getInt(1);
                        if(stat==0)
                        {
                            System.out.println("This book is not issued to this student so you cant return\n");
                            continue;
                        }

                        System.out.println("What is today's date");
                        date= in.next();
                         stmt=con.prepareStatement("SELECT due_date FROM Transactions WHERE book_id = ? and sroll=? ;");
                        stmt.setString(1,bid);//1 specifies the first parameter in the query
                        stmt.setString(2,sroll);//1 specifies the first parameter in the query
                         rs=stmt.executeQuery();
                        rs.next();
                        String date2=rs.getString(1);
                        if(date2.compareTo(date) < 0)
                        {
                            System.out.println("Your due date was "+date2+ "and today is"+date+"you have a FINE");
                        }
                        else{
                            System.out.println("returning book...\n");
                        }
                        stmt=con.prepareStatement("UPDATE Transactions SET cur_status = 0 WHERE book_id= ? AND sroll = ?;");
                        stmt.setString(1,bid);//1 specifies the first parameter in the query
                        stmt.setString(2,sroll);//2 specifies the first parameter in the query
                        int i=stmt.executeUpdate();
                        System.out.println(i+" record updated \n");
                        if(i==1) {
                            System.out.println("returned book...\n\n");
                        }
                        stmt=con.prepareStatement("UPDATE Books SET status = 0 WHERE book_id= ?;");
                        stmt.setString(1,bid);//1 specifies the first parameter in the query
                        stmt.execute();


                    }
                    if (ans==6) {
                        System.out.println(" Enter Student's Roll No");
                        sroll= in.next();
                        PreparedStatement stmt = con.prepareStatement("SELECT trans_id, book_id, due_date, sroll, sName, cur_status FROM Transactions where sroll = ? and cur_status = 1");
                        stmt.setString(1,sroll);
                        ResultSet rs = stmt.executeQuery();
                        while(rs.next()){
                            //Retrieve by column name
                            int tid  = rs.getInt("trans_id");
                            String book_id  = rs.getString("book_id");
                            String due_date = rs.getString("due_date");
                            sroll = rs.getString("sroll");
                            Integer cur_status = rs.getInt("cur_status");


                            //Display values
                            System.out.print("TranactionID: " + tid);
                            System.out.print(", catalogID: " + book_id);
                            System.out.print(", studentID: " + sroll);
                            System.out.print(", dueDate " + due_date);
                            System.out.println(", Was Book Returned(0 if returned): " + cur_status);
                        }

                        System.out.println("\n Above was the list of books student currently has, if empty, student dont have any book issued currently\n");
                    }
                    if (ans==7) {
                        System.out.println(" Enter Students Roll No");
                        sroll= in.next();
                        PreparedStatement stmt = con.prepareStatement("SELECT trans_id, book_id, due_date, sroll, sName, cur_status FROM Transactions where sroll = ? ");
                        stmt.setString(1,sroll);
                        ResultSet rs = stmt.executeQuery();

                        while(rs.next()){
                            //Retrieve by column name
                            int tid  = rs.getInt("trans_id");
                            String book_id  = rs.getString("book_id");
                            String due_date = rs.getString("due_date");
                            sroll = rs.getString("sroll");
                            Integer cur_status = rs.getInt("cur_status");


                            //Display values
                            System.out.print("TranactionID: " + tid);
                            System.out.print(", catalogID: " + book_id);
                            System.out.print(", studentID: " + sroll);
                            System.out.print(", dueDate " + due_date);
                            System.out.println(", Was Book Returned(0 if returned): " + cur_status);

                        }
                        System.out.println("\n Above was the total list of books student has issued at any time, if empty, he/she never issued any book\n");

                    }



                    else {
                        continue;


                    }



                }
                if (an==2) {
                    System.out.println("Select any number corresponding to the options:");
                    System.out.println("1. Search Book");
                    System.out.println("2. Check self status");
                    System.out.println("3. Check history of books issued");
                    System.out.println("4. Back to Main Menu");

                     ans = in.nextInt();
                    if (ans==1) {
                        System.out.println(" Enter Book's ID");
                         bid= in.next();
                        PreparedStatement stmt = con.prepareStatement("SELECT book_id, book_name, author, status FROM Books where book_id = ?");
                        stmt.setString(1,bid);
                        ResultSet rs = stmt.executeQuery();
                        rs.next();
                        int status  = rs.getInt("status");
                        String author = rs.getString("author");
                        String book = rs.getString("book_name");

                        //Display values
                        System.out.print("Book ID: " + bid);
                        System.out.print(", book Name: " + book);
                        System.out.print(", author: " + author);
                        if(status == 1)
                              System.out.println(", status: issued \n\n");
                        else
                            System.out.println(", status: available \n\n");



                    }
                    if (ans==2) {
                        System.out.println(" Enter Your Roll No");
                        sroll= in.next();
                        PreparedStatement stmt = con.prepareStatement("SELECT trans_id, book_id, due_date, sroll, sName, cur_status FROM Transactions where sroll = ? and cur_status = 1");
                        stmt.setString(1,sroll);
                        ResultSet rs = stmt.executeQuery();

                        while(rs.next()){
                            //Retrieve by column name
                            int tid  = rs.getInt("trans_id");
                            String book_id  = rs.getString("book_id");
                            String due_date = rs.getString("due_date");
                            sroll = rs.getString("sroll");
                            Integer cur_status = rs.getInt("cur_status");


                            //Display values
                            System.out.print("TranactionID: " + tid);
                            System.out.print(", catalogID: " + book_id);
                            System.out.print(", studentID: " + sroll);
                            System.out.print(", dueDate " + due_date);
                            if(cur_status == 0)
                                System.out.println(", status: returned \n\n");
                            else
                                System.out.println(", status: currently issued \n\n");

                        }
                        System.out.println("\n Above was the list of books you currently have, if empty, You dont have any book issued currently, relax ;)\n");





                    }
                    if (ans==3) {
                        System.out.println(" Enter Your Roll No");
                        sroll= in.next();
                        PreparedStatement stmt = con.prepareStatement("SELECT trans_id, book_id, due_date, sroll, sName, cur_status FROM Transactions where sroll = ? ");
                        stmt.setString(1,sroll);
                        ResultSet rs = stmt.executeQuery();

                        while(rs.next()){
                            //Retrieve by column name
                            int tid  = rs.getInt("trans_id");
                            String book_id  = rs.getString("book_id");
                            String due_date = rs.getString("due_date");
                            sroll = rs.getString("sroll");
                            Integer cur_status = rs.getInt("cur_status");


                            //Display values
                            System.out.print("TranactionID: " + tid);
                            System.out.print(", catalogID: " + book_id);
                            System.out.print(", studentID: " + sroll);
                            System.out.print(", dueDate " + due_date);
                            if(cur_status == 0)
                                System.out.println(", status: returned \n\n");
                            else
                                System.out.println(", status: currently issued \n\n");

                        }
                        System.out.println("\n Above was the total list of books you have issued at any time, if empty, You have never issued any book\n");





                    }

                    else {
                        continue;


                    }


                }
                else {
                    System.out.println("Exiting .....");
                    break;
                }


            }


//            Statement stmt=con.createStatement();
  //          ResultSet rs=stmt.executeQuery("select * from Books");
    //        while(rs.next())
     //           System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(2)+"  "+rs.getInt(1));
 //           con.close();
       }
              catch(Exception e){ System.out.println(e);}

        // TODO code application logic here
    }

}
