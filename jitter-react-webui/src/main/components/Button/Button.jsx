import './button.scss';

export function Button({children, href = ""}){
    return (
        <span className={"button"}>
            <a href={href}>
                {children}
            </a>
        </span>
    );
}