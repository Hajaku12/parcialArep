package org.example;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {

    @GetMapping("/linearsearch")
    public ResponseEntity<Map<String, String>> linearSearch(
            @RequestParam String list, @RequestParam String value){
        String[] items = list.split(",");
        int index = linearSearch(items, value);
        Map<String, String> response = new HashMap<>();
        response.put("operation", "linearSearch");
        response.put("inputlist", list);
        response.put("value", value);
        response.put("output", String.valueOf(index));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/binarysearch")
    public ResponseEntity<Map<String, String>> binarySearch(
            @RequestParam String list, @RequestParam String value){
        String[] items = list.split(",");
        Arrays.sort(items);
        int index = binarySearch(items, value, 0, items.length - 1);
        Map<String, String> response = new HashMap<>();
        response.put("operation", "binarySearch");
        response.put("inputlist", list);
        response.put("value", value);
        response.put("output", String.valueOf(index));
        return ResponseEntity.ok(response);
    }

    private int linearSearch(String[] list, String value){
        for (int i= 0; i < list.length; i++) {
            if (list[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    private int binarySearch (String[] list, String value, int left, int right){
        if (left > right) return -1;
        int mid = left + (right-left) /2;
        if (list[mid].equals(value)) return mid;
        if (list[mid].compareTo(value)>0){
            return binarySearch(list, value, left, mid -1);
        } else {
            return binarySearch(list, value, mid +1, right);
        }

    }

}
