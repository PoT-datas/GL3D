package com.pot.gl3d.act3;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.pot.gl3d.R;
import api.pot.gl3d.core.Object3dContainer;
import api.pot.gl3d.core.RendererActivity;
import api.pot.gl3d.objectPrimitives.SkyBox;
import api.pot.gl3d.parser.IParser;
import api.pot.gl3d.parser.Parser;
import api.pot.gl3d.vos.Light;
import api.pot.gl3d.vos.Number3d;

import static android.content.Context.SENSOR_SERVICE;

public class  ExampleAccelerometer extends RendererActivity implements SensorEventListener {
	private final float FILTERING_FACTOR = .3f;
	
	private SkyBox mSkyBox;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private Number3d mAccVals;
	private Object3dContainer mMonster;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccVals = new Number3d();
	}
	
	public void initScene()
	{
		scene.lights().add(new Light());
		
		mSkyBox = new SkyBox(5.0f, 2);
		mSkyBox.addTexture(SkyBox.Face.North, 	R.drawable.wood_back, 	"north");
		mSkyBox.addTexture(SkyBox.Face.East, 	R.drawable.wood_right, 	"east");
		mSkyBox.addTexture(SkyBox.Face.South, 	R.drawable.wood_back, 	"south");
		mSkyBox.addTexture(SkyBox.Face.West, 	R.drawable.wood_left, 	"west");
		mSkyBox.addTexture(SkyBox.Face.Up,		R.drawable.ceiling, 	"up");
		mSkyBox.addTexture(SkyBox.Face.Down, 	R.drawable.floor, 		"down");
		mSkyBox.scale().y = 0.8f;
		mSkyBox.scale().z = 2.0f;
		scene.addChild(mSkyBox);
		
		IParser parser = Parser.createParser(Parser.Type.MAX_3DS,
				getResources(), "min3d.sampleProject1:raw/monster_high", R.raw.monster_high, true);
		parser.parse();

		mMonster = parser.getParsedObject();
		mMonster.scale().x = mMonster.scale().y = mMonster.scale().z  = .1f;
		mMonster.position().y = -2.5f;
		mMonster.position().z = -3;
		scene.addChild(mMonster);
			
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
		
		// low-pass filter to make the movement more stable
		mAccVals.x = (float) (-event.values[1] * FILTERING_FACTOR + mAccVals.x * (1.0 - FILTERING_FACTOR));
		mAccVals.y = (float) (event.values[0] * FILTERING_FACTOR + mAccVals.y * (1.0 - FILTERING_FACTOR));
		
		scene.camera().position.x = mAccVals.x * .2f;
        scene.camera().position.y = mAccVals.y * .2f;
        
        scene.camera().target.x = -scene.camera().position.x;
        scene.camera().target.y = -scene.camera().position.y;
	}
}