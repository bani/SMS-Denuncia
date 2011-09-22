#ifndef CORE_H
#define CORE_H

#include <string>
#include <list>

namespace smsdenuncia {

namespace device {

enum DeviceEquipmentStatus {
	Available = 0,
	NotAvailable = 1
};

class DeviceEquipmentManager {
private:
	DeviceEquipmentManager& operator=(const DeviceEquipmentManager& r) {}
	DeviceEquipmentManager(const DeviceEquipmentManager& r) {}
public:
	DeviceEquipmentManager() {};
	virtual ~DeviceEquipmentManager() {};
	virtual int PutString(const std::string& value) = 0;
	virtual std::string GetString(void) = 0;
	virtual std::string SendCommand(std::string command) = 0;
	virtual DeviceEquipmentStatus GetStatus(void) const = 0;
};

class GpsManager : public DeviceEquipmentManager {
private:
	std::string _buffer;	
public:
	GpsManager();
	~GpsManager();
	int PutString(const std::string& value);
	std::string GetString(void);
	std::string SendCommand(std::string command);
	DeviceEquipmentStatus GetStatus(void) const;
};

class PersistenceManager : public DeviceEquipmentManager {
public:
	PersistenceManager();
	~PersistenceManager();
	const std::list<std::string>& QueryList(const std::string& listName);
	int PersistValue(const std::string& key, const std::string& value);
	DeviceEquipmentStatus GetStatus(void) const;
};

} // device

namespace core {

class Core {
public:
	Core();
	~Core();
private:
	const device::GpsManager * _gpsManager;
	const device::PersistenceManager * _persistenceManager;

	Core& operator=(const Core& r) {}
	Core(const Core& r) {}
public:
	void Initialize(void);
	const std::string GetCurrentView(void);
	int SetGpsManager(const device::GpsManager& gpsManager);
	int SetPersistenceManager(const device::PersistenceManager& persistenceManager);
};

} // core

} // smsdenuncia

#endif
