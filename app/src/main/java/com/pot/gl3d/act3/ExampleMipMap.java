package com.pot.gl3d.act3;

import android.graphics.Bitmap;

import com.pot.gl3d.R;
import api.pot.gl3d.Shared;
import api.pot.gl3d.Utils;
import api.pot.gl3d.core.Object3dContainer;
import api.pot.gl3d.core.RendererActivity;
import api.pot.gl3d.objectPrimitives.Box;
import api.pot.gl3d.vos.Light;

/**
 * Demonstrates visual difference between a texture with mipmapping versus a texture without.
 * The cube without the mipmapped texture displays distracting, aliasing artifacts.  
 * 
 * Note how mipmapping is set on or off at the TextureManager level.   
 * (It is not controlled at the Object3d level, or the TextureVO level)
 * 
 * Mipmapping requires the target hardware to support gl3d ES 1.1.
 * If it does not, the request to generate the MIP maps is ignored.
 */
public class ExampleMipMap extends RendererActivity
{
	Object3dContainer _holder;
	Object3dContainer _cubeWithMipMap;
	Object3dContainer _cubeWithoutMipMap;
	
	public void initScene() 
	{
		scene.lights().add(new Light());
		
		_holder = new Object3dContainer(0, 0);
		scene.addChild(_holder);
		
		_cubeWithMipMap = new Box(1.5f,1.5f,1.5f);
		_cubeWithMipMap.position().y = 1f;
		_holder.addChild(_cubeWithMipMap);

		_cubeWithoutMipMap = new Box(1.5f,1.5f,1.5f);
		_cubeWithoutMipMap.position().y = -1f;
		_holder.addChild(_cubeWithoutMipMap);
		
		//

		Bitmap b = Utils.makeBitmapFromResourceId(this, R.drawable.checkerboard);
		
		Shared.textureManager().addTextureId(b, "checkerboard_with_mipmap", true);
		Shared.textureManager().addTextureId(b, "checkerboard_without_mipmap", false);

		_cubeWithMipMap.textures().addById("checkerboard_with_mipmap");
		_cubeWithoutMipMap.textures().addById("checkerboard_without_mipmap");

		b.recycle();
	}
	
	@Override 
	public void updateScene() 
	{
		_holder.rotation().y +=1;
		_holder.rotation().z +=.25;
	}
}
