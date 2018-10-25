package com.example.dell.rocketlauncher;

class ImageClass {
    private String imageName;
    private int imgURL;

    public ImageClass(String imageName, int imgURL) {
        this.imageName = imageName;
        this.imgURL = imgURL;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getImgURL() {
        return imgURL;
    }

    public void setImgURL(int imgURL) {
        this.imgURL = imgURL;
    }
}
