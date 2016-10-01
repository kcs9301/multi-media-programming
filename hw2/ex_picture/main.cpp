//#include <opencv2/core.hpp>
//#include <opencv2/imgcodecs.hpp>
//#include <opencv2/highgui.hpp>
#include "opencv2/opencv.hpp"

#include <iostream>
#include <string>

using namespace cv;
using namespace std;

int main(int argc, char** argv)
{


	//Windows Environment, OPENCV_OPENCL_DEVICE="Intel:GPU:0"
	/*
	//Check devices
	ocl::setUseOpenCL(true);
	if (!ocl::haveOpenCL())
	{
	cout << "OpenCL is not available..." << endl;
	//return;
	}

	cv::ocl::Context context;
	if (!context.create(cv::ocl::Device::TYPE_GPU))
	{
	cout << "Failed creating the context..." << endl;
	//return;
	}

	cout << context.ndevices() << " GPU devices are detected." << endl; //This bit provides an overview of the OpenCL devices you have in your computer
	for (int i = 0; i < context.ndevices(); i++)
	{
	cv::ocl::Device device = context.device(i);
	cout << "name:              " << device.name() << endl;
	cout << "available:         " << device.available() << endl;
	cout << "imageSupport:      " << device.imageSupport() << endl;
	cout << "OpenCL_C_Version:  " << device.OpenCL_C_Version() << endl;
	cout << endl;
	}

	//set device
	cv::ocl::Device(context.device(1));
	*/

	//main
	Mat image, frame, edges;
	float e1, e2, time;
	String imageName;

	//load image
	
	if (argc > 1)
		imageName = argv[1];
	else
		imageName = "lena.jpg"; // by default

	image = imread(imageName, IMREAD_COLOR); // Read the file

	if (image.empty())                      // Check for invalid input
	{
		cout << "Could not open or find the image" << std::endl;
		return -1;
	}

	//Processing
	e1 = getTickCount();

	cvtColor(image, edges, COLOR_BGR2GRAY);
	GaussianBlur(edges, edges, Size(7, 7), 1.5, 1.5);
	Canny(edges, edges, 0, 30, 3);

	e2 = getTickCount();
	time = (e2 - e1) / getTickFrequency();
	cout << "Elapsed time: " << time  << " seconds" << std::endl;

	//Display
	namedWindow("edges", WINDOW_AUTOSIZE); // Create a window for display.
	imshow("edges", edges);                // Show our image inside it.

	//wait
	waitKey(0); // Wait for a keystroke in the window

	return 0;
}
