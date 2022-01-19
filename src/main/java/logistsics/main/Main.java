package logistsics.main;

import Logistics.connections.DAO;
import Logistics.connections.connectionImplements.LocationDAO;
import Logistics.entyties.Locations;
import logistsics.converter.ConvertToXLSX;
import logistsics.converter.EntytyType;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DAO dAO = new LocationDAO();
        List<Locations> locations = dAO.getAll();
        ConvertToXLSX convertToXLSX = new ConvertToXLSX("locations_.xlsx");
        convertToXLSX.setLocations(locations);
        try {
            convertToXLSX.locationsToXLSX(convertToXLSX.locationHeaders, EntytyType.Location);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
