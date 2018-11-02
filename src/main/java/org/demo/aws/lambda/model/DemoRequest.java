package org.demo.aws.lambda.model;

public class DemoRequest {
    private int id;
    private String name;

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

    @Override
    public String toString() {
        return "DemoRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
