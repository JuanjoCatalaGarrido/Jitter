import './navbarItem.scss';

export function NavbarItem({children, href=""}){
    return (
        <span className={"navbar-item"}>
            <a href={href}>{children}</a>
        </span>
    );
}