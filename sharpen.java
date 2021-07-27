private void sharpenActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:ERROR
        
        BufferedImage image = NewBi;
        // A 3x3 kernel that sharpens an image
        Kernel kernel = new Kernel(3, 3,
                new float[]{
                    -1, -1, -1,
                    -1, 9, -1,
                    -1, -1, -1});
 
        BufferedImageOp op = new ConvolveOp(kernel);
        image = op.filter(image, null);
        NewBi = image;
        ImageIcon imageicon = new ImageIcon(image);

        jLabel1.setIcon(ResizeImage(imageicon));
    }                        