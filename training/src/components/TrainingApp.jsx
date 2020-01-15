import React,{Component} from 'react'
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom'


class TrainingApp extends Component {

    render() {
        return (
            <Router>
                <Switch>
                    <Route path="/trainer" component={TrainerComponent}/>

                </Switch>
            </Router>
        )
    }
}

export default TrainingApp;