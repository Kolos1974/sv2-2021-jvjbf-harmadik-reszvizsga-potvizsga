package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApartmentRental {
    private List<Apartment> apartments = new ArrayList<>();

    public void addApartment(Apartment apartment){
        apartments.add(apartment);
    }


    public List<Apartment> findApartmentByLocation(String location){
        return apartments.stream()
                .filter(a-> a.getLocation().equals(location))
                .toList();
    }

    public List<Apartment> findApartmentByExtras (String... extras){
        List<String> text = new ArrayList<>();
        for (int i = 0; i<extras.length; i++){
            text.add(extras[i]);
        }

        return apartments.stream()
                .filter(a->a.getExtras().containsAll(text))
                .toList();
    }


    public boolean isThereApartmentWithBathroomType(BathRoomType bathRoomType){
        return apartments.stream()
                .anyMatch(a->a.getBathRoomType()==bathRoomType);
    }

    public List<Integer> findApartmentsSize(){
        return apartments.stream()
                .map(a -> a.getSize())
                .toList();
    }


}
