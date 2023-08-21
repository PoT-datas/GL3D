package com.pot.gl3d.act3;

import com.pot.gl3d.R;
import api.pot.gl3d.core.Object3dContainer;
import api.pot.gl3d.core.RendererActivity;
import api.pot.gl3d.parser.IParser;
import api.pot.gl3d.parser.Parser;
import api.pot.gl3d.vos.Light;

/**
 * How to load a model from a .obj file
 * 
 * @author dennis.ippel
 * 
 */
public class ExampleLoadObjFile extends RendererActivity {
	private Object3dContainer objModel;

	@Override
	public void initScene() {
		
		scene.lights().add(new Light());
		
		IParser parser = Parser.createParser(Parser.Type.OBJ,
				getResources(), "android.resource://com.pot.gl3d/" + R.raw.camaro_obj, R.raw.camaro_obj, true);
		parser.parse();

		objModel = parser.getParsedObject();
		objModel.scale().x = objModel.scale().y = objModel.scale().z = .7f;
		scene.addChild(objModel);
	}

	@Override
	public void updateScene() {
		objModel.rotation().x++;
		objModel.rotation().z++;
	}
}
