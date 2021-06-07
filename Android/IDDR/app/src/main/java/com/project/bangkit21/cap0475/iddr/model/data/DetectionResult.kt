package com.project.bangkit21.cap0475.iddr.model.data

import android.graphics.RectF

/**
 * DetectionResult
 *      A class to store the visualization info of a detected object.
 */
data class DetectionResult(val boundingBox: RectF, val text: String, val textLabel: String)
