package com.pot.gl3d.act3;

import com.pot.gl3d.R;
import api.pot.gl3d.animation.AnimationObject3d;
import api.pot.gl3d.core.RendererActivity;
import api.pot.gl3d.parser.IParser;
import api.pot.gl3d.parser.Parser;
import api.pot.gl3d.vos.Light;

public class ExampleLoadMD2File extends RendererActivity {
	private AnimationObject3d ogre;

	@Override
	public void initScene() {
		
		scene.lights().add(new Light());
		
		IParser parser = Parser.createParser(Parser.Type.MD2,
				getResources(), "min3d.sampleProject1:raw/ogro", R.raw.ogro,false);
		parser.parse();

		ogre = parser.getParsedAnimationObject();
		ogre.scale().x = ogre.scale().y = ogre.scale().z = .07f;
		ogre.rotation().z = -90;
		ogre.rotation().x = -90;
		scene.addChild(ogre);
		ogre.setFps(70);
		ogre.play();
	}

	@Override
	public void updateScene() {

	}

}
