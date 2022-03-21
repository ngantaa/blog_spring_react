import "../css/ThreadCard.css"

const ThreadCard = (props) => {

return(
                <a className="threadcard" href={"/thread/" + props.thread.id} key={props.thread.id}  >
                    <li>
                        <div className="header">
                            <h4>{props.thread.threadName}</h4>
                        </div>
                        <div className="body">
                            {props.thread.content}
                        </div>
                        <div className="footer">
                            {props.thread.createdAt.substring(0,10)}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            {props.thread.user.username}
                        </div>
                    </li>
                </a>
    );
}

export default ThreadCard;