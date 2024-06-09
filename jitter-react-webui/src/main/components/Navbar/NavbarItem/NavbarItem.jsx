import './navbarItem.scss';

export function NavbarItem({children, className, onClickHandle, href=""}){
    return (
        <span onClick={onClickHandle} className={`navbar-item`}>
            <a className={className} href={href}>{children}</a>
        </span>
    );
}