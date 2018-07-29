pragma solidity ^0.4.19;

contract Immunization{
    struct ChildDetails{
        string ChildName;
        string MotherName;
        string DOB;
        uint PhoneNumber;
    }
    struct Schedule{
        uint immunizationCode;
        uint countTotal;
        uint countCompleted; 
    }
    mapping(string => ChildDetails) private Children;
    mapping(string => Schedule[]) private ChildSchedule;
    function registerChild(string childname, string mothername, string dob, uint phoneNumber, string detailshash) public returns (bool status){
        bool Status = false;
        Schedule[] storage childSchedules;
        childSchedules.push(Schedule({immunizationCode:1, countTotal:3, countCompleted:0}));
        childSchedules.push(Schedule({immunizationCode:2, countTotal:5, countCompleted:0}));
        childSchedules.push(Schedule({immunizationCode:3, countTotal:2, countCompleted:0}));
        Children[detailshash]=ChildDetails({ChildName: childname, MotherName: mothername, DOB: dob, PhoneNumber: phoneNumber});
        ChildSchedule[detailshash]=childSchedules;
        Status = true;
        return (Status);
    }
    function getChild(string detailsHash)public returns (string childname, string mothername, string dob, uint phoneNumber, uint[] codes, uint[] count, uint[] countcompleted){
        ChildDetails storage child = Children[detailsHash];
        uint[] memory Codes = new uint[](ChildSchedule[detailsHash].length);
        uint[] memory Count = new uint[](ChildSchedule[detailsHash].length);
        uint[] memory Countcompleted = new uint[](ChildSchedule[detailsHash].length);
        for(uint i=0; i<ChildSchedule[detailsHash].length; i++){
            Codes[i]= ChildSchedule[detailsHash][i].immunizationCode;
            Count[i]= ChildSchedule[detailsHash][i].countTotal;
            Countcompleted[i]= ChildSchedule[detailsHash][i].countCompleted;
        }
        return (child.ChildName, child.MotherName, child.DOB, child.PhoneNumber, Codes, Count, Countcompleted);
    }
    function updateChild(string detailsHash, uint planCode, uint countcompleted)public returns(bool status){
        bool Status=false;
        for(uint i=0; i<ChildSchedule[detailsHash].length; i++){
            if(ChildSchedule[detailsHash][i].immunizationCode==planCode){
                ChildSchedule[detailsHash][i].countCompleted=countcompleted;
                Status=true;
            }
        }
        return (Status);
    }
}