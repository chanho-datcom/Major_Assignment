pragma solidity ^0.4.25;
pragma experimental ABIEncoderV2;


contract ShowDiagnosis {
    address public owner;
    mapping(bytes32 => bool) public whiteList; // byte32 타입의 인풋값을 받아 bool타입으로 저장하는 whiteList를 선언.
    uint index = 0;

    struct Transcript{
        string Transen;
        string Tranrec;
        uint256 Tranval;
        string Dname;
        string date;
        uint256 ETP;
        string DocOp;
    }

    Transcript [] A;

    constructor() public {
        owner = msg.sender;
    }


    modifier onlyowner() {
        require(msg.sender==owner);
        _;
    }

    function addTrans(string Transen, string Tranrec, uint Tranval, string Dname, string date, uint256 ETP, string DocOp) public { // 거래 기록을 저장하는 함수
        A.push(Transcript(Transen, Tranrec, Tranval, Dname, date, ETP, DocOp));
        Transen = A[index].Transen;
        Tranrec = A[index].Tranrec;
        Tranval = A[index].Tranval;
        Dname = A[index].Dname;
        date = A[index].date;
        ETP = A[index].ETP;
        DocOp = A[index].DocOp;
        index = index + 1;
    }

    function getTrans(uint index) public view returns (string Transen, string Tranrec, uint Tranval, string Dname, string date, uint256 ETP, string DocOp) { // 저장된 거래기록을 불러오는 함수
        if(whiteList[keccak256(msg.sender)] == true || msg.sender==owner) {
            Transen = A[index].Transen;
            Tranrec = A[index].Tranrec;
            Tranval = A[index].Tranval;
            Dname = A[index].Dname;
            date = A[index].date;
            ETP = A[index].ETP;
            DocOp = A[index].DocOp;
        }

    }
    function ShowSelectTrans(uint startnum, uint endnum) public view returns(string getTrans, string getTranrec, uint getTranval, string getDname, string getdate, uint256 getETP, string getDocOp) { // 선택한 구간의 거래기록을 불러오는 함수
        if(startnum > endnum){

        }else if(!(endnum != startnum)){

            getTrans = A[startnum].Transen;
            getTranrec = A[startnum].Tranrec;
            getTranval = A[startnum].Tranval;
            getDname = A[startnum].Dname;
            getdate = A[startnum].date;
            getETP = A[startnum].ETP;
            getDocOp = A[startnum].DocOp;

        }else{

            for(uint strat = startnum; strat < endnum; strat = strat + 1){
                getTrans = A[strat].Transen;
                getTranrec = A[strat].Tranrec;
                getTranval = A[strat].Tranval;
                getDname = A[strat].Dname;
                getdate = A[strat].date;
                getETP = A[strat].ETP;
                getDocOp = A[strat].DocOp;

            }
        }
    }

    event Transfer(address indexed from, address indexed to, uint256 value);
    event DeleteFromWhitelist(address indexed target);
    event RejectedPaymentToWhitelistedAddr(address indexed from, address indexed to, uint256 value);

    function Whitelisting(address _addr) external onlyowner { // 주소에 해당하는 화이트리스트 값을 true로 바꾸어주는 함수 == 접근권한 부여
        whiteList[keccak256(_addr)] = true;
    }

    function deleteFromWhitelist(address _addr) external onlyowner { // 주소에 해당하는 화이트리스트 값을 false로 바꾸어주는 함수 == 접근권한 회수
        whiteList[keccak256(_addr)] = false;
    }

    function IsWhitelisted(address _addr) public view returns (bool whitelisted) { // 주소에 해당하는 화이트리스트 값을 확인하는 함수 == 접근권한 확인
        return whiteList[keccak256(_addr)];
    }

    function getBalance(address _addr) external view returns (uint256){ // 계좌에 남은 잔액을 확인하는 함수
        return _addr.balance;
    }

    function transfer(address _to) payable onlyowner{ // 오류를 탐지하고 송금하는 함수
        if(msg.sender.balance < msg.value) throw; // 보내는사람(블럭주인)의 잔액이 송금량보다 적으면 rollback시킨다.
        if(_to.balance +  msg.value < _to.balance) throw; // 받는사람의 잔액에 송금된 금액을 더했을때 기존 잔액보다 적으면 rollback시킨다.

        if(whiteList[keccak256(_to)] == true) {
            _to.transfer(msg.value);
        }else {
            msg.sender.transfer(msg.value);
        }
    }
}