import java.util.ArrayList;
import java.util.List;

public interface IReferences<T> {
    void write();
    ArrayList<T> getListElements();
}
