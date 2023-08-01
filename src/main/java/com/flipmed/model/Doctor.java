import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

public class Doctor {
    int id;
    String name;
    Specialization Specialization;
    int rating;

    @Override
    public boolean equals(Object other) {
        if( !(other instanceof Doctor)){
            return false;
        }
        return this.id == other.id;
    } 
}
