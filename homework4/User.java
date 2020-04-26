package homework4;
import java.util.*;
/**
 Name: Qian Cai
 * Created by Qian Cai on 2019/10/2.
 */
public class User implements Comparable<User> {
    private String name;
    private int id;
    private Date birth;
    public User (String name, int id, Date birth){
        this.name = name;
        this.id = id;
        this.birth = birth;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (other == null || (this.getClass() != other.getClass())){
            return false;
        }

        User guest = (User) other;
        return(this.id == guest.id) && (this.name != null && name.equals(guest.name)) && (this.birth != null && birth.equals(guest.birth));
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31*result + id;
        result = 31*result + (name !=null ? name.hashCode() : 0);
        result = 31*result + (birth !=null ? birth.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(User o) {
        return this.id - o.id;
    }
    public static void main(String [] args)
    {
        User m=new User("Cassie",001,new Date(1996-03-27));
        User u=new User("Merry",002,new Date(1997-11-06));
        User m2=new User("Cassie",001,new Date(1996-03-27));
        System.out.println(m.equals(u));
        System.out.println(m.equals(m));
        System.out.println(m.equals(m2));
        System.out.println(m.compareTo(u));
        System.out.println(m.compareTo(m));
        System.out.println(m.compareTo(m2));
        System.out.println(m.hashCode());
        System.out.println(u.hashCode());
        System.out.println(m2.hashCode());
        if(m.hashCode()==u.hashCode())
        {System.out.println("true"); }
        else {System.out.println("false");}
        if(m.hashCode()==m2.hashCode())
        {System.out.println("true"); }
        else {System.out.println("false");}



    }

}
