private void blurringActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        if (OriginalBi == null) {
        throw new IllegalArgumentException("tidak ada gambar");
    }
         
          BufferedImage gmb = null;
         
          gmb = NewBi;
        
          BufferedImage blurred = blur(gmb, filter, filterWidth);
          
          NewBi = blurred;
        ImageIcon imageicon = new ImageIcon(blurred);

     //   gambar = new JLabel1(imageicon);
        jLabel1.setIcon(ResizeImage(imageicon));
          
    }