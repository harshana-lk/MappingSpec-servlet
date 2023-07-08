package me.harshu.mappingspec;

public class MyBean {
    String id;
    String name;

    public MyBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public MyBean() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyBean{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }

}
