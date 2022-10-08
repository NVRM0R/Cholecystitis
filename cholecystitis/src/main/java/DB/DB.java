package DB;

import com.clinic.cholecystitis.model.Cholecystitis;

import java.util.ArrayList;
import java.util.Iterator;

public class DB {
    ArrayList<Cholecystitis> data;
    int currentId;
    public DB(){
        this.data = new ArrayList<Cholecystitis>();
        this.currentId = 0;
    }
    public int push(Cholecystitis newData){
        newData.setId(this.currentId);
        this.currentId+=1;
        data.add(newData);
        return this.currentId-1;
    }
    public Iterator<Cholecystitis> getIter(){
        return data.iterator();
    }
    public void remove(Cholecystitis obj){
        data.remove(obj);
    }
}
