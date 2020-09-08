/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 *
 * @flow
 */

import {type Quaternion, type Vec3} from 'react-360-web';
import {type CameraController} from 'react-360-web';

export default class LuchoCameraController implements CameraController {
  

  constructor() {
    }

  

  fillCameraProperties(position: Vec3, rotation: Quaternion): boolean {
    return true;
  }
}