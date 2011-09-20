#include <string>
#include "core.h"

namespace hal {

GpsManager::GpsManager() {
	
}

GpsManager::~GpsManager() {

}

int GpsManager::PutString(const std::string value) {
	_buffer(value);
}

std::string GpsManager::GetString(void) {
	return _buffer;
}

std::string GpsManager::SendCommand(std::string command) {
	
}

} // hal

namespace core {

Core::Core() {

}

Core::~Core() {

}

void Core::Initialize(void) {

}

const std::string Core::GetCurrentView(void) {

}

const int Core::SetGpsManager(const hal::GpsManager gpsManager) {

}

} // core