package com.app.org;

import android.os.Bundle;

import org.ros.address.InetAddressFactory;
import org.ros.android.BitmapFromCompressedImage;
import org.ros.android.RosActivity;
import org.ros.android.view.RosImageView;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;

import sensor_msgs.CompressedImage;

public class PCLActivity extends RosActivity {

    private RosImageView<CompressedImage> image;

    public PCLActivity() {
        super("Image Transport", "Image Transport");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcl);
        image = (RosImageView<sensor_msgs.CompressedImage>) findViewById(R.id.image);
        image.setTopicName("/usb_cam/image_raw/compressed");
        image.setMessageType(sensor_msgs.CompressedImage._TYPE);
        image.setMessageToBitmapCallable(new BitmapFromCompressedImage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        NodeConfiguration nodeConfiguration =
                NodeConfiguration.newPublic(InetAddressFactory.newNonLoopback().getHostAddress(),
                        getMasterUri());
        nodeMainExecutor.execute(image, nodeConfiguration.setNodeName("android/video_view"));
    }
}
