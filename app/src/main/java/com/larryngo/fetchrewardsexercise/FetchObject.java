package com.larryngo.fetchrewardsexercise;

import java.util.Comparator;

/*
    OVERVIEW

    Contains the entire information about the object. This shows info about the listid, id, and name;
 */

public class FetchObject {
    //it is very important that these names aren't altered. This is because retrofit uses the exact names that are shown
    //in the json file.
    private int listId;
    private int id;
    private String name;

    public FetchObject(int listId, int id, String name) {
        this.listId = listId;
        this.id = id;
        this.name = name;
    }

    public static Comparator<FetchObject> COMPARE_BY_LISTID = new Comparator<FetchObject>() {
        public int compare(FetchObject obj1, FetchObject obj2) {
            return obj1.listId - obj2.listId;
        }
    };

    public static Comparator<FetchObject> COMPARE_BY_ID = new Comparator<FetchObject>() {
        public int compare(FetchObject obj1, FetchObject obj2) {
            return obj1.id - obj2.id;
        }
    };

    public static Comparator<FetchObject> COMPARE_BY_NAME = new Comparator<FetchObject>() {
        public int compare(FetchObject obj1, FetchObject obj2) {
            return obj1.name.compareTo(obj2.name);
        }
    };

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
