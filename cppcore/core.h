#ifndef CORE_H
#define CORE_H

#include <string>

namespace smsdenuncia {

namespace hal {

class HardwareManager {
private:
	HardwareManager& operator=(const HardwareManager& r) {}
	HardwareManager(const HardwareManager& r) {}
public:
	virtual HardwareManager(void) = 0;
	virtual ~HardwareManager(void) = 0;
	virtual int PutString(const std::string value) = 0;
	virtual std::string GetString(void) = 0;
	virtual std::string SendCommand(std::string command) = 0;
};

class GpsManager : public HardwareManager {
private:
	std::string _buffer;	
public:
	GpsManager(void);
	~GpsManager(void);
	int PutString(const std::string value);
	std::string GetString(void);
	std::string SendCommand(std::string command);
};

} // hal

namespace core {

class Core {
public:
	Core();
	~Core();
private:
	Core& operator=(const Core& r) {}
	Core(const Core& r) {}
public:
	void Initialize(void);
	const std::string GetCurrentView(void);
	const int SetGpsManager(const hal::GpsManager gpsManager);
};

} // core

} // smsdenuncia

#endif
