package  org.com.User_Location.User_Location.controller;


import org.com.User_Location.User_Location.models.UsersLocation;
import org.com.User_Location.User_Location.pojo.LocationsPojo;
import org.com.User_Location.User_Location.repo.UsersLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/location")
public class UsersLocationController {
    private final UsersLocationRepository usersLocationRepository;
    @Autowired
    public UsersLocationController(UsersLocationRepository usersLocationRepository) {
        this.usersLocationRepository = usersLocationRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/setLocation")
    public ResponseEntity<?> addLocation(@RequestBody UsersLocation usersLocation) {
        UsersLocation saveUsersLocation = this.usersLocationRepository.save(usersLocation);
        return ResponseEntity.ok(saveUsersLocation);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getLocation")
    public ResponseEntity<?> getLocation() {
        return ResponseEntity.ok(this.usersLocationRepository.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateLocation/{locationID}")
    public ResponseEntity<?> updateLocation(@RequestBody UsersLocation usersLocation) {
        UsersLocation saveUsersLocation = this.usersLocationRepository.save(usersLocation);
        return ResponseEntity.ok(saveUsersLocation);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteLocation/{locationID}")
    public void deleteLocation(@PathVariable int locationID){
        this.usersLocationRepository.deleteById(locationID);
    }

    @GetMapping("/getLocation/{noOfUsers}")
    public ResponseEntity<?> getUsersLocation(@PathVariable int noOfUsers) {
        List<UsersLocation> allUsers = this.usersLocationRepository.findAll();
        List <LocationsPojo> usersLocations = getUsersLocation(noOfUsers, allUsers);
        return ResponseEntity.ok(usersLocations);
    }

    private List<LocationsPojo> getUsersLocation(int noOfUsers, List<UsersLocation> allUsers) {
        Map<LocationsPojo, Double> locationMap = new HashMap<>();
        List<LocationsPojo> finalLocations = new ArrayList<>();
        for(UsersLocation location : allUsers){
            LocationsPojo locPojo = new LocationsPojo();
            locPojo.setUserName(location.getUserName());
            locPojo.setUserLocation(location.getUserLocation());
            locPojo.setLatitude(location.getLatitude());
            locPojo.setLongitude(location.getLongitude());

            double distance = distance(0, 0, location.getLatitude(), location.getLongitude());
            locPojo.setDistance(distance);
            locationMap.put(locPojo, distance);
        }

        List<Map.Entry<LocationsPojo, Double>> list = new ArrayList<>(locationMap.entrySet());
        //Using Entry's comparingByValue() method for sorting in ascending order
        list.sort(Map.Entry.comparingByValue());
        for (int counter = 0; counter < noOfUsers; counter++)
            finalLocations.add(list.get(counter).getKey());

        return finalLocations;
    }

    public double distance(int lat1, int lon1, int lat2, int lon2) {
        int theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        dist = dist * 1.609344;  // kilometer

        return (dist);
    }

    // This function converts decimal degrees to radians
    private double deg2rad(int deg) {
        return (deg * Math.PI / 180.0);
    }

    //  This function converts radians to decimal degrees
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
