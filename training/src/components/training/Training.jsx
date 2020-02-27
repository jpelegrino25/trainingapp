import React from 'react'
import AuthenticationService from '../../api/services/AuthenticationService.js';
import TrainingService from '../../api/services/TrainingService.js'

class Training extends React.Component {

    constructor(props) {
        super(props);
        this.state= {
            training : {
                trainingList :[]
            }
        }
    }

    componentDidMount() {
        AuthenticationService.handleInterceptor();
        this.trainingRefresh();
    }

    loadTrainings=() => {
        TrainingService.fingAll()
        .then(response=>{
            let trainingUpdate={...this.state.training}
            trainingUpdate.trainingList=response.data
            this.setState({training:trainingUpdate})
        })
    }

    editTraining=(trainingId,e)=> {        
        this.props.history.push(`/trainings/${trainingId}`);
    }

    deletTraining=(trainingId,e)=> {       
        TrainingService.delete(trainingId)
        .then(()=>{
            this.trainingRefresh()
        })
        
    }

    addNewTraining=()=> {
        const trainingId=-1;
        this.props.history.push(`/trainings/${trainingId}`);
    }

    trainingRefresh=()=> {
        this.loadTrainings();
    }

    render() {

        const {training}=this.state

        let trainingRender=
        training.trainingList.map(training=> {
            return  <tr key={training.id}>
                        <td>{training.description}</td>                        
                        <td><button 
                            onClick={(e)=>this.editTraining(training.id,e)}
                            className="btn btn-primary">
                            Edit</button></td>

                            <td><button 
                            onClick={(e)=>this.deletTraining(training.id,e)}
                            className="btn btn-danger">
                            Delete</button></td>
                    </tr>
                })

        return (
            <div className="container">
            <h1 className="text-center">Training Maintanace</h1>
            <div>
                <table className="table my-3">
                    <thead>
                        <tr>
                            <th>Description</th>                            
                            <th colSpan="2">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {trainingRender}                       
                    </tbody>

                </table>
            </div>
            <button 
            className="btn btn-success"
            onClick={this.addNewTraining}
            >New Training</button>
        </div>
        )
    }
}

export default Training;