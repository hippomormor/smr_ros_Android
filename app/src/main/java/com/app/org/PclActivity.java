package com.app.org;

import android.os.Bundle;

import org.ros.address.InetAddressFactory;
import org.ros.android.RosActivity;
import org.ros.node.NodeConfiguration;
import org.ros.node.NodeMainExecutor;
import org.ros.android.view.visualization.PointCloudView;

public class PclActivity extends RosActivity {

    private PointCloudView pcdVisualizationView;

    public PclActivity() {
        super("Point Cloud Viewer", "Point Cloud Viewer");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcl);

        pcdVisualizationView = (PointCloudView) findViewById(R.id.pcd_visualization);
        pcdVisualizationView.setControlMode(PointCloudView.BUTTONS_AND_GESTURES);
        pcdVisualizationView.onCreate(PclActivity.this, "/vision/cloud");

    }

    @Override
    protected void init(NodeMainExecutor nodeMainExecutor) {
        pcdVisualizationView.init(nodeMainExecutor);

        NodeConfiguration nodeConfiguration =
                NodeConfiguration.newPublic(InetAddressFactory.newNonLoopback().getHostAddress(),
                        getMasterUri());

        nodeMainExecutor.execute(pcdVisualizationView.getNodeMain(), nodeConfiguration.setNodeName("android/pcd_view"));
    }
}
