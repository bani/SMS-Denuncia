#include <string>

#include <iostream>
#include <list>
#include <memory>
#include "core.h"


namespace smsdenuncia {

namespace device {

GpsManager::GpsManager() {
	
}

GpsManager::~GpsManager() {

}

int GpsManager::PutString(const std::string& value) {
	_buffer = std::string(value);
	return 0;
}

std::string GpsManager::GetString(void) {
	return _buffer;
}

std::string GpsManager::SendCommand(std::string command) {
	return std::string("");
}

DeviceEquipmentStatus GpsManager::GetStatus() const {
	int gpsStatus;
	std::cout << "GpsManager: inform the GPS status: ";
	std::cin >> gpsStatus;
	return static_cast<smsdenuncia::device::DeviceEquipmentStatus>(gpsStatus);
}

PersistenceManager::PersistenceManager() {

}

PersistenceManager::~PersistenceManager() {

}

const std::list<std::string>& PersistenceManager::QueryList(const std::string& listName) {
	std::auto_ptr< std::list< std::string > > resultList(new std::list < std::string >);
	resultList->push_back(std::string("Teste"));
	return static_cast<const std::list< std::string >& >(*resultList.release());	
}

int PersistenceManager::PersistValue(const std::string& key, const std::string& value) {
	return 0;
}

DeviceEquipmentStatus PersistenceManager::GetStatus() const {
	return Available;
}

} // device

namespace core {

Core::Core() {
	_gpsManager = nullptr;
	_persistenceManager = nullptr;
}

Core::~Core() {

}

void Core::Initialize(void) {
	std::cout << "Core: initializing..." << std::endl;
	
	std::cout << "Core: checking GPS device status..." << std::endl;
	int gpsStatus = this->_gpsManager->GetStatus();
	
	{
		using namespace smsdenuncia::device;
		
		if (gpsStatus == Available) std::cout << "Core: Gps Available" << std::endl;
		else if (gpsStatus == NotAvailable) std::cout << "Core: Gps is not Available" << std::endl;
		else std::cout << "Core: Unknown Gps status. Assuming Not Available" << std::endl;
	}
}

const std::string Core::GetCurrentView(void) {
	return static_cast<const std::string>(std::string(""));
}

int Core::SetGpsManager(const device::GpsManager& gpsManager) {
	_gpsManager = &gpsManager;
	return 0;
}

int Core::SetPersistenceManager(const device::PersistenceManager& persistenceManager) {
	_persistenceManager = &persistenceManager;
	return 0;
}

} // core

} // smsdenuncia


int main (void) {
	std::cout << "Inicializando core..." << std::endl;
	
	smsdenuncia::core::Core myCore;
	smsdenuncia::device::GpsManager gpsManager;
	
	myCore.SetGpsManager(gpsManager);
	
	myCore.Initialize();
	
	std::cout << "Encerrando..." << std::endl;
	return 0;
}