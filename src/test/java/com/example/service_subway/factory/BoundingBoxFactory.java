package com.example.service_subway.factory;

import com.example.service_subway.core.model.Coordinate;
import com.example.service_subway.core.model.Region;

import static com.example.service_subway.factory.BoundingBoxArrayFactory.boundingBoxArray;

public class BoundingBoxFactory {
    public static Region.BoundingBox boundingBox() {
        var bboxArray = boundingBoxArray();
        return new Region.BoundingBox(new Coordinate(bboxArray[0], bboxArray[2]), new Coordinate(bboxArray[1], bboxArray[3]));
    }

}
