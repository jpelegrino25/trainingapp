
import React from 'react'
import RegisterService from '../../api/services/RegisterService';
import AuthenticationService from '../../api/services/AuthenticationService';
import AttendanceService from '../../api/services/AttendanceService';


class Attendance extends React.Component {

    constructor(props) {
        super(props);
        this.state={
            sessionRegisterList:[],
            attendance:null,
            attendances:[]
        }
        
    }

    componentDidMount() {
        AuthenticationService.handleInterceptor();        
        this.getAttendance()
    }

    getAttendance=()=> {        
        let userId=this.props.match.params.userId;
        let sessionId=this.props.match.params.sessionId;

        RegisterService.findAttendance(userId,sessionId)
        .then(response=> {
            let attendanceListUpdate=[];
            response.data.forEach(sessionReg => {
                attendanceListUpdate.push(this.createNewAttendance(sessionReg));
            });
          
            this.setState({attendances:attendanceListUpdate})
        }).catch(err=>console.log(err))
    }

    changeField=(e)=>{
        const {name,checked}=e.target;
        let attendanceListUpdate=this.state.attendances;
        let index=e.currentTarget.getAttribute('data-index');

        let attendance=attendanceListUpdate[index];
        let attendanceUpdate={...attendance,[name]:checked}
        attendanceListUpdate[index]=attendanceUpdate;

        this.setState({attendances:attendanceListUpdate})
    }

    createNewAttendance=(sessionReg)=> {
        let attendance={id:null,
        student:sessionReg.registerSessionId.user,        
        session:sessionReg.registerSessionId.session,
        status:{
            id:1
        },
        assisted:false
        };       
        
        return attendance;
    }

    saveAttendance=()=> {
        let attendance={
            attendances:this.state.attendances
        };

        AttendanceService.saveAttendance(attendance)
        .then(()=>{
            console.log('successful save.');
        }).catch(err=>console.log(err))
    }

    render() {
        const {attendances}=this.state;

        let attendanceRender=
        attendances.map((att,index)=>{
             return    <tr key={index}>
               <td>{att.session.sessionName}</td>
                <td>{att.student.firstname}</td>
                <td>{att.student.lastname}</td>
                <td><input type="checkbox"
                    name="assisted" 
                    data-index={index}
                    value={att.assisted}
                    onChange={this.changeField}
                /></td>
            </tr>
        })
        return <div>
             <table className="table my-3">
                    <thead>
                        <tr>
                            <th>Session Name</th>  
                            <th>First Name</th>   
                            <th>Last Name</th>                             
                            <th>Is Present?</th>
                        </tr>
                    </thead>
                    <tbody>
                        {attendanceRender}                       
                    </tbody>

                </table>

                <div className="form-group">
                    <button className="btn btn-primary"
                    onClick={this.saveAttendance}>Send</button>
                </div>
                
        </div>
    }

    

}

export default Attendance;