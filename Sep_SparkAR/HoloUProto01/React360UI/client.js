import {ReactInstance,Location, Math as VRMath} from 'react-360-web';
import LuchoCameraController from './LuchoCameraController'; 

function init(bundle, parent, options = {}) {
	
	const r360 = new ReactInstance(bundle, parent, {
		fullScreen: true,
		...options,
	});
	
	const mainCameraController = new LuchoCameraController(r360._eventLayer);
  
  
  r360.renderToLocation(
    r360.createRoot("ModelSample",r360.vrState),
    r360.getDefaultLocation(),
	);	

	r360.controls.addCameraController(mainCameraController);
}

window.React360 = {init};

